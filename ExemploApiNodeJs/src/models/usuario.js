//dependencias
let usuarios = require('../../listaUsuarios');

class Usuario {
    find() {
        return new Promise((resolve, reject) => {
            if(usuarios.length > 0)
                resolve(usuarios);
            resolve('nenhum usuario cadastrado');
        });
    }

    findOne(id) {
        return new Promise((resolve, reject) => {
            for(let i = 0; i < usuarios.length; i++){
                if(usuarios[i].id === parseInt(id))
                    resolve(usuarios[i]);
            }

            reject('usuario nao encontrado');
        });
    }

    insert(nome) {
        return new Promise((resolve, reject) => {
            if(usuarios.length > 0) {
                usuarios.reverse();

                const id = usuarios[0].id + 1;

                usuarios.reverse();

                usuarios.push({ id, nome });

                resolve('usuario cadastrado');
            } else {
                usuarios.push({ id: 1, nome });
                resolve('usuario cadastrado');
            }
        });
    }

    deletar(id) {
        return new Promise((resolve, reject) => {
            for(let i = 0; i < usuarios.length; i++) {
                if(usuarios[i].id === parseInt(id)){
                    usuarios.splice(i, 1);
                    resolve('usuario removido');
                }
            }

            reject('usuario nao encontrado');
        });
    }

    update(id, nome) {
        return new Promise((resolve, reject) => {
            for(let i = 0; i < usuarios.length; i++) {
                if(usuarios[i].id === parseInt(id)) {
                    usuarios[i] = { id: parseInt(id), nome };
                    resolve('usuario atualizado');
                }
            }

            reject('usuario nao encontrado');
        });
    }
}

module.exports = Usuario;
