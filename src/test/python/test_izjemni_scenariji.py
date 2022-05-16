import pexpect
import os, stat

def test_izjemni_scenariji():
    aplikacija = pexpect.pexpect()
    filename = "datoteka"

    try:
        aplikacija.expect("command> ")
        aplikacija.send("add")
        aplikacija.expect("add> EMSO: ")
        aplikacija.send("1234567891234")
        aplikacija.expect("add> NAME: ")
        aplikacija.send("Janez")
        aplikacija.expect("add> SURNAME: ")
        aplikacija.send("Novak")
        aplikacija.expect("add> AGE: ")
        aplikacija.send("30")
        aplikacija.expect("add> VACCINE: ")
        aplikacija.send("Moderna")
        aplikacija.expect(">>OK")

        fd = open(filename, "w")
        fd.close()

        mode = os.stat(filename)[stat.ST_MODE]
        os.chmod(filename, mode & ~stat.S_IWRITE)

        aplikacija.expect("command> ")
        aplikacija.send("save datoteka")
        aplikacija.expect(">>I/O Error IO error datoteka (Access is denied)")

        print "PASSED\ttest_izjemni_scenariji_ni_dovoljenja"
    except:
        print "FAILED\ttest_izjemni_scenariji_ni_dovoljenja"


    finally:

        aplikacija.kill()


if __name__ == "__main__":
    test_izjemni_scenariji()
