import { App, supertest, chai } from './config/helper';
import * as jwt from 'jwt-simple';
import * as HTTPStatus from 'http-status';

describe('Testes de integracao', () => {

	const config = require('../../server/config/env/config')();
	const models = require('../../server/models');

	let id;
	let token;

	const userTest = {
		id: 100,
		name: 'User Test',
		email: 'user.test@email.com',
		password: '123456'
	};

	const userDefault = {
		id: 101,
		name: 'User Default',
		email: 'user.default@email.com',
		password: '123456'
	};

	beforeEach(done => {

		models.sequelize.sync()
			.then(() => models.User.destroy({ where: {} }))
			.then(() => models.User.create(userDefault))
			.then(user => models.User.create(userTest))
			.then(user => token = jwt.encode({ id: user.id }, config.secret))
			.then(() => done());

	});

	describe('POST /api/token', () => {

		it('Deve gerar um token', done => {

			const credentials = {
				email: userTest.email,
				password: userTest.password
			}

			supertest(App)
				.post('/token')
				.send(credentials)
				.end((err, res) => {

					chai.expect(res.status).to.be.equal(HTTPStatus.OK);
					chai.expect(res.body).not.empty;
					chai.expect(res.body.token).to.be.equal(token);
					done(err);

				});

		});

		it('Não deve gerar um token', done => {

			const credentials = {
				email: 'email@email.com',
				password: 'password'
			}

			supertest(App)
				.post('/token')
				.send(credentials)
				.end((err, res) => {

					chai.expect(res.status).to.be.equal(HTTPStatus.UNAUTHORIZED);
					chai.expect(res.body).to.be.empty;
					done(err);

				});

		});

	});

	describe('GET /api/users/all', () => {

		it('Deve retornar um array com todos os usuarios', done => {

			supertest(App)
				.get('/api/users/all')
				.set('Content-Type', 'application/json')
				.set('Authorization', `JWT ${token}`)
				.end((err, res) => {

					chai.expect(res.status).to.equal(HTTPStatus.OK);
					chai.expect(res.body.payload).to.be.an('array');
					chai.expect(res.body.payload[1].id).to.equal(userTest.id);
					chai.expect(res.body.payload[1].name).to.equal(userTest.name);
					chai.expect(res.body.payload[1].email).to.equal(userTest.email);
					done(err);

				});

		});

	});

	describe('GET /api/users/:id', () => {

		it('Deve retornar um json com um unico usuario', done => {

			supertest(App)
				.get(`/api/users/${userDefault.id}`)
				.set('Content-Type', 'application/json')
				.set('Authorization', `JWT ${token}`)
				.end((err, res) => {

					chai.expect(res.status).to.equal(HTTPStatus.OK);
					chai.expect(res.body.payload.id).to.equal(userDefault.id);
					chai.expect(res.body.payload.name).to.equal(userDefault.name);
					chai.expect(res.body.payload.email).to.equal(userDefault.email);
					done(err);

				});

		});

	});

	describe('POST /api/users/create', () => {

		it('Deve criar um novo usuario', done => {

			const user = {
				id: 2,
				name: 'Teste',
				email: 'teste@email.com',
				password: '123456'
			};

			supertest(App)
				.post('/api/users/create')
				.set('Content-Type', 'application/json')
				.set('Authorization', `JWT ${token}`)
				.send(user)
				.end((err, res) => {

					chai.expect(res.status).to.equal(HTTPStatus.OK);
					chai.expect(res.body.payload.id).to.equal(user.id);
					done(err);

				});

		});

	});

	describe('PUT /api/users/:id/update', () => {

		it('Deve atualizar um usuario', done => {

			const user = {
				name: 'Outro nome',
				email: 'outro.email@email.com'
			};

			supertest(App)
				.put(`/api/users/${userTest.id}/update`)
				.send(user)
				.set('Content-Type', 'application/json')
				.set('Authorization', `JWT ${token}`)
				.end((err, res) => {

					chai.expect(res.status).to.equal(HTTPStatus.OK);
					chai.expect(res.body.payload).to.be.an('array');
					chai.expect(res.body.payload[0]).to.be.equal(1);
					done(err);

				});

		});

	});

	describe('DELETE /api/users/:id/destroy', () => {

		it('Deve deletar um usuario', done => {

			supertest(App)
				.delete(`/api/users/${userTest.id}/destroy`)
				.set('Content-Type', 'application/json')
				.set('Authorization', `JWT ${token}`)
				.end((err, res) => {

					chai.expect(res.status).to.equal(HTTPStatus.OK);
					chai.expect(res.body.payload).to.be.equal(1);
					done(err);

				});

		});

	});

});
