import { Request, Response } from 'express';
import UserController from './controller';

class UserRoutes {

	private userCtrl: UserController;

	constructor() {

		this.userCtrl = new UserController();

	}

	index(req: Request, res: Response) {

		this.userCtrl.getAll(req, res);

	}

	create(req: Request, res: Response) {

		this.userCtrl.createUser(req, res);

	}

	findOne(req: Request, res: Response) {

		this.userCtrl.getById(req, res);

	}

	update(req: Request, res: Response) {

		this.userCtrl.updateUser(req, res);

	}

	destroy(req: Request, res: Response) {

		this.userCtrl.deleteUser(req, res);

	}

}

export default UserRoutes;
