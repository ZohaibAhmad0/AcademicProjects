from gui.main_window import MainWindow
from models.library_system import LibrarySystem

def main():
    library_system = LibrarySystem()  # Now works without database parameter
    app = MainWindow(library_system)
    app.mainloop()

if __name__ == "__main__":
    main()
