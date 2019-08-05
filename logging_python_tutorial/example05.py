import logging
import logging.config

logging.config.fileConfig('logging.conf')

logger = logging.getLogger('example05')

logger.debug('OK')
logger.info('OK')
logger.warning('OK')
logger.error('OK')
logger.critical('OK')
