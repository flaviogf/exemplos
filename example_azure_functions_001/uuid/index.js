const uuid = require("uuid/v4");

module.exports = async function (context, req) {
  const guid = uuid();

  const statusCode = 200;

  const body = { guid };

  context.res = { statusCode, body };
};
