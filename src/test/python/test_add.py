import pexpect


def test_add():
    aplikacija = pexpect.pexpect()

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

        print "PASSED\ttest_add_successful"
    except:
        print "FAILED\ttest_add_successful"

    try:
        aplikacija.expect("command> ")
        aplikacija.send("add")
        aplikacija.expect("add> EMSO: ")
        aplikacija.send("12")
        aplikacija.expect(">>Invalid input data")

        print "PASSED\ttest_add_emso_invalid"
    except:
        print "FAILED\ttest_add_emso_invalid"

    try:
        aplikacija.expect("command> ")
        aplikacija.send("add")
        aplikacija.expect("add> EMSO: ")
        aplikacija.send("1234567891234")
        aplikacija.expect(">>Person already exists")

        print "PASSED\ttest_add_emso_already_exists"
    except:
        print "FAILED\ttest_add_emso_already_exists"

    try:
        aplikacija.expect("command> ")
        aplikacija.send("add")
        aplikacija.expect("add> EMSO: ")
        aplikacija.send("1234567891239")
        aplikacija.expect("add> NAME: ")
        aplikacija.send("Janez")
        aplikacija.expect("add> SURNAME: ")
        aplikacija.send("Novak")
        aplikacija.expect(">>Person already exists")

        print "PASSED\ttest_add_name_already_exists"
    except:
        print "FAILED\ttest_add_name_already_exists"

    try:
        aplikacija.expect("command> ")
        aplikacija.send("add")
        aplikacija.expect("add> EMSO: ")
        aplikacija.send("1234567891239")
        aplikacija.expect("add> NAME: ")
        aplikacija.send("Tone")
        aplikacija.expect("add> SURNAME: ")
        aplikacija.send("Podobnik")
        aplikacija.expect("add> AGE: ")
        aplikacija.send("999")
        aplikacija.expect(">>Invalid input data")

        print "PASSED\ttest_add_age_invalid"
    except:
        print "FAILED\ttest_add_age_invalid"

    try:
        aplikacija.expect("command> ")
        aplikacija.send("add")
        aplikacija.expect("add> EMSO: ")
        aplikacija.send("1234567891239")
        aplikacija.expect("add> NAME: ")
        aplikacija.send("Tone")
        aplikacija.expect("add> SURNAME: ")
        aplikacija.send("Podobnik")
        aplikacija.expect("add> AGE: ")
        aplikacija.send("30")
        aplikacija.expect("add> VACCINE: ")
        aplikacija.send("Kr neki")
        aplikacija.expect(">>Invalid input data")

        print "PASSED\ttest_add_cepivo_invalid"
    except:
        print "FAILED\ttest_add_cepivo invalid"


    finally:
        aplikacija.kill()


if __name__ == "__main__":
    test_add()
