import Queue from "bull";

import redisConfig from "../config/redis";

import RegistrationMail from "../app/jobs/RegistrationMail";

export default new Queue(RegistrationMail.key, { redis: redisConfig });
