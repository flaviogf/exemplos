/** @type {typeof import('@adonisjs/lucid/src/Lucid/Model')} */
const Model = use('Model');

class Loja extends Model {
  static scopeAtividades(scope) {
    return scope.with('atividades');
  }

  atividades() {
    return this.hasMany('App/Models/Atividade');
  }
}

module.exports = Loja;
