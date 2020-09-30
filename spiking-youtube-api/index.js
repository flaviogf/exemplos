const debug = require("debug");
const express = require("express");
const { google } = require("googleapis");

const CLIENT_ID = "YOUR_CLIENT_ID";
const CLIENT_SECRET = "YOUR_CLIENT_SECRET";
const CHANNEL_ID = "YOUR_CHANNEL_ID";

const oauth2Client = new google.auth.OAuth2({
  clientId: CLIENT_ID,
  clientSecret: CLIENT_SECRET,
  redirectUri: "http://localhost:3333/callback",
});

const url = oauth2Client.generateAuthUrl({
  access_type: "offline",
  scope: [
    "https://www.googleapis.com/auth/youtube",
    "https://www.googleapis.com/auth/youtube.force-ssl",
    "https://www.googleapis.com/auth/youtubepartner",
  ],
});

debug("app")(url);

const app = express();

app.use(express.urlencoded({ extended: true }));
app.use(express.json());

app.get("/callback", async (req, res) => {
  try {
    const { code } = req.query;

    const { tokens } = await oauth2Client.getToken(code);

    oauth2Client.setCredentials(tokens);

    const youtube = google.youtube({
      auth: oauth2Client,
      version: "v3",
    });

    const { data: channels } = await youtube.channels.list({
      id: CHANNEL_ID,
      part: "contentDetails",
    });

    const playlistId =
      channels.items[0].contentDetails.relatedPlaylists.uploads;

    const { data: videos } = await youtube.playlistItems.list({
      part: "contentDetails",
      playlistId,
      maxResults: 100,
    });

    const videoIds = videos.items.map((it) => it.contentDetails.videoId);

    const tasks = videoIds.map((id) =>
      youtube.videos.rate({ id, rating: "like" })
    );

    await Promise.all(tasks);

    debug("app")("Success");
  } catch (err) {
    debug("app")(err);
  }

  return res.send("OK");
});

app.listen(3333, () => debug("app")("It is running"));
