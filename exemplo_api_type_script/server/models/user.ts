import { DataTypes, Sequelize } from 'sequelize';

export default function (sequelize: Sequelize, dataTypes: DataTypes) {

	const User = sequelize.define('User', {

		id: {
			type: dataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},
		name: {
			type: dataTypes.STRING,
			allowNull: false,
			validate: {
				notEmpty: true
			}
		},
		email: {
			type: dataTypes.STRING,
			allowNull: false,
			validate: {
				notEmpty: true
			}
		},
		password: {
			type: dataTypes.STRING,
			allowNull: false,
			validate: {
				notEmpty: true
			}
		}

	});

	return User;

}
