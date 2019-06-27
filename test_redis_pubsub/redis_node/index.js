const redis = require('redis')

client = redis.createClient()

count = 0

setInterval(() => {
  client.publish('news', ++count)
  console.log(`> send news ${count}`)
}, 1000)
