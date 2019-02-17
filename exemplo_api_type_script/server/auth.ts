import * as passport from 'passport';
import { Strategy, StrategyOptions, ExtractJwt, VerifiedCallback } from 'passport-jwt';
const config = require('./config/env/config')();
import User from './modules/Users/service';

export default function AuthConfig() {

	const UserService = new User();

	const opts: StrategyOptions = {
		secretOrKey: config.secret,
		jwtFromRequest: ExtractJwt.fromAuthHeaderWithScheme('jwt')
	}

	passport.use(new Strategy(opts, (payload: any, done: VerifiedCallback) => {

		console.log(payload);

		UserService.getById(payload.id)
			.then(user => {

				if (user) {

					return done(null, {
						id: user.id,
						email: user.email
					});

				}

				return done(null, false);

			})
			.catch(erro => {

				return done(erro, false);

			});

	}));

	return {
		initialize: () => passport.initialize(),
		authenticate: () => passport.authenticate('jwt', { session: false })
	}

}
