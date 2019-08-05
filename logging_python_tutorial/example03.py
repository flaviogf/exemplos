import logging

logging.basicConfig(filename='example03.log', format='%(asctime)s %(message)s', level=logging.DEBUG, datefmt='%Y-%m-%d %H:%M:%S')

log = logging.getLogger('example03')

log.debug('Hello')
