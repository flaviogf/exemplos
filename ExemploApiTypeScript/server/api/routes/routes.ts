import { Application, Request, Response } from 'express';
import UserRoutes from '../../modules/Users/routes';
import TokenRoute from '../../modules/auth/auth';

class Routes {

	private router: UserRoutes;
	private tokenRoute: TokenRoute;
	private auth;

	constructor(private app: Application, auth: any) {

		this.router = new UserRoutes();
		this.tokenRoute = new TokenRoute();
		this.auth = auth;
		this.getRoutes();

	}

	getRoutes() {

		this.app.route('/api/users/all').all(this.auth.authenticate()).get(this.router.index.bind(this.router));
		this.app.route('/api/users/create').all(this.auth.authenticate()).post(this.router.create.bind(this.router));
		this.app.route('/api/users/:id').all(this.auth.authenticate()).get(this.router.findOne.bind(this.router));
		this.app.route('/api/users/:id/update').all(this.auth.authenticate()).put(this.router.update.bind(this.router));
		this.app.route('/api/users/:id/destroy').all(this.auth.authenticate()).delete(this.router.destroy.bind(this.router));
		this.app.route('/token').post(this.tokenRoute.auth);

	}

}

export default Routes;
