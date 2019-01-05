import CoreData

class AlunoRepository: IAlunoRepository {

    private var context: NSManagedObjectContext?

    init(context: NSManagedObjectContext?) {
        self.context = context
    }

    func insere(_ aluno: Aluno) throws {
        if let context = self.context {
            let data = AlunoData(context: context)
            data.nome = aluno.nome
            data.email = aluno.email
            data.endereco = aluno.endereco
            data.imagem = aluno.imagem
            data.id = UUID()
            try context.save()
        }
    }

    func lista() throws -> Array<Aluno> {
        if let context = self.context {
            let fetchRequest = NSFetchRequest<AlunoData>(entityName: "AlunoData")
            let data = try context.fetch(fetchRequest)
            return data.map {
                Aluno(nome: $0.nome ?? "", email: $0.email ?? "", endereco: $0.endereco ?? "", imagem: $0.imagem ?? "", id: $0.id)
            }
        }

        return []
    }

    func deleta(_ aluno: Aluno) throws {
        if let context = self.context, let id = aluno.id, let data: AlunoData = try self.busca(por: id) {
            context.delete(data)
            try context.save()
        }
    }

    func busca(por id: UUID) throws -> Aluno? {
        if let data: AlunoData = try self.busca(por: id) {
            return Aluno(nome: data.nome ?? "", email: data.email ?? "", endereco: data.endereco ?? "", imagem: data.imagem ?? "", id: data.id)
        }

        return nil
    }

    func atualiza(_ aluno: Aluno) throws {
        if let context = self.context, let id = aluno.id, let data: AlunoData = try self.busca(por: id) {
            data.nome = aluno.nome
            data.email = aluno.email
            data.endereco = aluno.endereco
            data.imagem = aluno.imagem
            try context.save()
        }
    }

    func busca(por id: UUID) throws -> AlunoData? {
        if let context = self.context {
            let fetchRequest = NSFetchRequest<AlunoData>(entityName: "AlunoData")
            fetchRequest.predicate = NSPredicate(format: "%K == %@", "id", id as CVarArg)
            fetchRequest.fetchLimit = 1
            return try context.fetch(fetchRequest).first
        }

        return nil
    }
}
