import unittest

from example01 import main


class Example01Tests(unittest.TestCase):
    def test_should_result_one_plus_result_two_equal_eight(self):
        result = main()

        self.assertEqual(8, result)
