import { Request, Response } from 'express';
import * as HTTPStatus from 'http-status';
import * as _ from 'lodash';
import User from './service';
import onSucess from '../../api/response/onSuccess';
import onError from '../../api/response/onError';
import onDbError from '../../api/response/onDbErro';

class UserController {

	private user: User;

	constructor() {

		this.user = new User();

	}

	getAll(req: Request, res: Response) {

		this.user.getAll()
			.then(_.partial(onSucess, res))
			.catch(_.partial(onError, res, 'Erro ao buscar todos usuarios'));

	}

	createUser(req: Request, res: Response) {

		this.user.create(req.body)
			.then(_.partial(onSucess, res))
			.catch(_.partial(onDbError, res))
			.catch(_.partial(onError, res, 'Erro ao cadastrar usuario'));

	}

	getById(req: Request, res: Response) {

		const userId = parseInt(req.params.id);

		this.user.getById(userId)
			.then(_.partial(onSucess, res))
			.catch(_.partial(onError, res, 'Erro ao buscar o usuario'));

	}

	deleteUser(req: Request, res: Response) {

		const id = parseInt(req.params.id);

		this.user.delete(id)
			.then(_.partial(onSucess, res))
			.catch(_.partial(onError, res, 'Erro ao deletar o usuario'));

	}

	updateUser(req: Request, res: Response) {

		const userId = parseInt(req.params.id);

		this.user.update(userId, req.body)
			.then(_.partial(onSucess, res))
			.catch(_.partial(onError, res, 'Erro ao atualizar o usuario'));

	}

}

export default UserController;
