from field_ui import *
from field import *

def main():
    command, quit = "", False
    field = "n/a"
    while(quit):
        command = command.split()
        if command[0] == "quit":
            quit = True
        elif command[0] == "help":
            pass
        elif command[0] == "create":
            pass
        elif command[0] == "delete":
            pass
        elif command[0] == "add":
            pass
        elif command[0] == "list":
            pass
        elif command[0] == "save":
            pass
        elif command[0] == "load":
            pass

def printHelp():
    pass

def createField():
    pass

def deleteElement():
    pass

def addElement():
    pass

def listElements():
    pass

def saveField(field):
    if field == "n/a":
        print("Field is null.")
    else:
        pass

def loadField():
    pass

if __name__ == "__main__":
    main()
