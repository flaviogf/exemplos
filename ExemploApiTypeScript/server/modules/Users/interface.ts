export interface IUser {

	readonly id: number;
	name: string;
	email: string;
	password: string;

}

export interface IUserDetail extends IUser {

}

export const createUser = ({ id, name, email, password }: any): IUser => ({ id, name, email, password });

export const createUsers = (data: any[]): IUser[] => data.map(createUser);

export const createUserById = ({ id, name, email, password }: any): IUserDetail => ({ id, name, email, password });

export const createUserByEmail = ({ id, name, email, password }: any): IUserDetail => ({ id, name, email, password });
