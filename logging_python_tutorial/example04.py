import logging

logger = logging.getLogger('example04')
logger.setLevel(logging.DEBUG)


handler = logging.StreamHandler()
handler.setLevel(logging.DEBUG)

fmt = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s', datefmt='%H:%M:%S')

handler.setFormatter(fmt)

logger.addHandler(handler)

logger.debug('01')
logger.info('02')
logger.warning('03')
logger.error('04')
logger.critical('05')
