import * as express from 'express';
import { Application } from 'express';
import * as morgan from 'morgan';
import * as bodyParser from 'body-parser';
import { errorHandler } from './errorHandler';
import Routes from './routes/routes';
import AuthConfig from '../auth';

class Api {

	public express: Application;
	public auth = AuthConfig();

	constructor() {

		this.express = express();
		this.middleware();
		this.routes();

	}

	private middleware() {

		this.express.use(morgan('dev'));
		this.express.use(bodyParser.urlencoded({ extended: true }));
		this.express.use(bodyParser.json());
		this.express.use(this.auth.initialize());
		this.express.use(errorHandler);

	}

	private routes() {

		new Routes(this.express, this.auth);

	}

}

export default new Api().express;
