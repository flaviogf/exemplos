import sched
from datetime import datetime, timedelta
from time import ctime, time

s = sched.scheduler(timefunc=time)


def saytime():
    print(ctime())
    reschedule()


def reschedule():
    new_target = datetime.now() + timedelta(seconds=5)
    new_target = new_target.timestamp()
    s.enterabs(new_target, 0, saytime)


reschedule()

s.run()
