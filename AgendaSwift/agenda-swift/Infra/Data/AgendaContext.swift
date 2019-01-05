import UIKit
import CoreData

class AgendaContext {

    static var context: NSManagedObjectContext? {
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else {
            return nil
        }

        return appDelegate.persistentContainer.viewContext
    }
}
