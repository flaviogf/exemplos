import sched

s = sched.scheduler()


def sum():
    print(1 + 1)

    reschedule()


def reschedule():
    s.enter(5, 2, sum)


if __name__ == '__main__':
    try:
        reschedule()
        s.run()
    except KeyboardInterrupt:
        pass
