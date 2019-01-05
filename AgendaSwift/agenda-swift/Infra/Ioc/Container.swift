class Container {

    // MARK: - Repositories

    var alunoRepository: IAlunoRepository {
        return AlunoRepository(context: AgendaContext.context)
    }
    
    var imagemRepository: IImagemRepository {
        return ImagemRepository()
    }

    // MARK: - Services

    var alunoService: IAlunoService {
        return AlunoService(alunoRepository: self.alunoRepository, imagemRepository: self.imagemRepository)
    }

    // MARK: - Application

    var alunoApplication: IAlunoApplication {
        return AlunoApplication(alunoService: self.alunoService)
    }

    // MARK: - Presenter

    var alunosPresenter: IAlunosPresenter {
        return AlunosPresenter(alunoApplication: self.alunoApplication)
    }

    var alunoPresenter: IAlunoPresenter {
        return AlunoPresenter(alunoApplication: self.alunoApplication)
    }

    // MARK: - Instancia

    private static var container: Container?

    static var instancia: Container {
        if (container == nil) {
            container = Container()
        }

        return container!
    }
}
