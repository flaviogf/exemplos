import { IUser, IUserDetail, createUser, createUsers, createUserById, createUserByEmail } from './interface';
import * as Bluebird from 'bluebird';
const model = require('../../models');

export default class User implements IUser {

	public id: number;
	public name: string;
	public email: string;
	public password: string;

	constructor() {

	}

	create(user: any) {

		return model.User.create(user)
			.then(createUser);

	}

	getAll(): Bluebird<IUser[]> {

		return model.User.findAll({
			order: ['name']
		}).then(createUsers);

	}

	getById(id: number): Bluebird<IUser> {

		return model.User.findOne({
			where: { id }
		}).then(createUser);

	}

	getByEmail(email: string): Bluebird<IUser> {

		return model.User.findOne({
			where: { email }
		}).then(createUser);

	}

	update(id: number, user: any): Bluebird<any> {

		return model.User.update(user, {
			where: { id },
			fields: ['name', 'email', 'password']
		});

	}

	delete(id: number): Bluebird<any> {

		return model.User.destroy({
			where: { id }
		});

	}

}
