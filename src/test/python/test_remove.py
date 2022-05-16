import pexpect


def test_remove():
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

        aplikacija.expect("command> ")
        aplikacija.send("remove 1234567891235")
        aplikacija.expect(">>OK")

        print "PASSED\ttest_remove_by_emso_successful"
    except:
        print "FAILED\ttest_remove_by_emso_successful"

    try:
        aplikacija.expect("command> ")
        aplikacija.send("remove")
        aplikacija.expect("remove> NAME: ")
        aplikacija.send("Janez")
        aplikacija.expect("remove> SURNAME: ")
        aplikacija.send("Novak")
        aplikacija.expect(">>OK")

        print "PASSED\ttest_remove_by_name_successful"
    except:
        print "FAILED\ttest_remove_by_name_successful"

    try:
        aplikacija.expect("command> ")
        aplikacija.send("remove 1234567891234")
        aplikacija.expect(">>Person does not exist")

        print "PASSED\ttest_remove_by_emso_person_does_not_exist"
    except:
        print "FAILED\ttest_remove_by_emso_person_does_not_exist"

    try:
        aplikacija.expect("command> ")
        aplikacija.send("remove")
        aplikacija.expect("remove> NAME: ")
        aplikacija.send("Janez")
        aplikacija.expect("remove> SURNAME: ")
        aplikacija.send("Novak")
        aplikacija.expect(">>Person does not exist")

        print "PASSED\ttest_remove_by_name_person_does_not_exist"
    except:
        print "FAILED\ttest_remove_by_name_person_does_not_exist"

    try:
        aplikacija.expect("command> ")
        aplikacija.send("remove x")
        aplikacija.expect(">>Invalid input data")

        print "PASSED\ttest_remove_invalid_input_data"
    except:
        print "FAILED\ttest_remove_invalid_input_data"


    finally:
        aplikacija.kill()


if __name__ == "__main__":
    test_remove()
