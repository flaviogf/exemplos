import logging

logging.basicConfig(filename='example02.log', level=logging.DEBUG)

log = logging.getLogger('example02')


log.debug('01')
log.info('02')
log.warning('03')
