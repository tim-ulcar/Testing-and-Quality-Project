import pexpect


def test_print():
    aplikacija = pexpect.pexpect()

    try:
        aplikacija.expect("command> ")
        aplikacija.send("print")
        aplikacija.expect(">>No. of Persons: 0")

        print "PASSED\ttest_print_empty"
    except:
        print "FAILED\ttest_print_empty"

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

        aplikacija.expect("command> ")
        aplikacija.send("add")
        aplikacija.expect("add> EMSO: ")
        aplikacija.send("1234567891235")
        aplikacija.expect("add> NAME: ")
        aplikacija.send("Tone")
        aplikacija.expect("add> SURNAME: ")
        aplikacija.send("Zamuda")
        aplikacija.expect("add> AGE: ")
        aplikacija.send("40")
        aplikacija.expect("add> VACCINE: ")
        aplikacija.send("Pfizer")
        aplikacija.expect(">>OK")

        aplikacija.expect("command> ")
        aplikacija.send("add")
        aplikacija.expect("add> EMSO: ")
        aplikacija.send("1234567891236")
        aplikacija.expect("add> NAME: ")
        aplikacija.send("Lojze")
        aplikacija.expect("add> SURNAME: ")
        aplikacija.send("Horvat")
        aplikacija.expect("add> AGE: ")
        aplikacija.send("50")
        aplikacija.expect("add> VACCINE: ")
        aplikacija.send("AstraZeneca")
        aplikacija.expect(">>OK")

        aplikacija.expect("command> ")
        aplikacija.send("print")
        aplikacija.expect(">>No. of Persons: 3")
        aplikacija.expect("1234567891236\tHorvat, Lojze\t50\tAstraZeneca")
        aplikacija.expect("1234567891234\tNovak, Janez\t30\tModerna")
        aplikacija.expect("1234567891235\tZamuda, Tone\t40\tPfizer")

        print "PASSED\ttest_print_multiple"
    except:
        print "FAILED\ttest_print_multiple"

    try:
        aplikacija.expect("command> ")
        aplikacija.send("print x")
        aplikacija.expect(">>Invalid argument")

        print "PASSED\ttest_print_invalid_argument"
    except:
        print "FAILED\ttest_print_invalid_argument"


    finally:
        aplikacija.kill()


if __name__ == "__main__":
    test_print()
