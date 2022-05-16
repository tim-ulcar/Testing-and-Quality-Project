import pexpect


def test_count():
    aplikacija = pexpect.pexpect()

    try:
        aplikacija.expect("command> ")
        aplikacija.send("count")
        aplikacija.expect(">>No. of Persons: 0")

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
        aplikacija.send("count")
        aplikacija.expect(">>No. of Persons: 1")

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
        aplikacija.send("count")
        aplikacija.expect(">>No. of Persons: 2")

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
        aplikacija.send("count")
        aplikacija.expect(">>No. of Persons: 3")

        print "PASSED\ttest_count_successful"
    except:
        print "FAILED\ttest_count_successful"

    try:
        aplikacija.expect("command> ")
        aplikacija.send("count x")
        aplikacija.expect(">>Invalid argument")

        print "PASSED\ttest_count_invalid_argument"
    except:
        print "FAILED\ttest_count_invalid_argument"


    finally:
        aplikacija.kill()


if __name__ == "__main__":
    test_count()
