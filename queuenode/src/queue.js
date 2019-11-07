import 'dotenv/config'

import Queue from "./lib/Queue";

import RegistrationMail from "./app/jobs/RegistrationMail";

Queue.process(RegistrationMail.handle);

Queue.on('failed', (job, error) => {
  console.error(`Job failed`, job)
  console.error(error)
})
