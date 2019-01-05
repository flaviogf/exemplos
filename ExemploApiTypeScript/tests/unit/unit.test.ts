import { supertest, chai } from './config/helper';
import User from '../../server/modules/Users/service';

describe('Testes Unitarios do Controller', () => {

	describe('Metodo Create', () => {

		it('Deve criar um novo usuario', () => {

			const novoUsuario = {
				name: 'flavio',
				email: 'fernandes',
				password: '123456'
			}

			const user = new User();

			return user.create(novoUsuario)
				.then(data => {

					chai.expect(data).to.have.all.keys('id', 'name', 'email', 'password');

				});

		});

	});

	describe('Metodo Update', () => {

		it('Deve atualizar um usuario', () => {

			const usuarioAtualizado = {
				name: 'usuario atualizado',
				email: 'emailatualizado@email.com'
			};

			const user = new User();

			return user.update(4, usuarioAtualizado)
				.then(data => {

					chai.expect(data[0]).to.equal(1);

				});

		});

	});

	describe('Metodo Get Users', () => {

		it('Deve retorna uma lista de usuarios', () => {

			const user = new User();

			return user.getAll()
				.then(data => {

					chai.expect(data).to.be.an('array');
					chai.expect(data[0]).to.have.all.keys('id', 'name', 'email', 'password');

				});

		});

	});

	describe('Metodo Delete', () => {

		it('Deve deletar um usuario', () => {

			const user = new User();

			return user.delete(3)
				.then(data => {

					chai.expect(data).to.equal(1);

				});

		});

	});

	describe('Metodo Get By Id', () => {

		it('Deve retornar um unico usuario', () => {

			const user = new User();

			return user.getById(4)
				.then(usuario => {

					chai.expect(usuario).to.have.all.keys('id', 'name', 'email', 'password');

				});

		});

	});

	describe('Metodo Get By Email', () => {

		it('Deve retornar um unico usuario', () => {

			const user = new User();

			return user.getByEmail('user.default@email.com')
				.then(usuario => {

					chai.expect(usuario).to.have.all.keys('id', 'name', 'email', 'password');

				});

		});

	});

});
