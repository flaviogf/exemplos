"""
x -> study_hours
y -> grade
"""
import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns

x = [20, 12, 14, 15, 18, 9, 5, 4, 8,
     13, 14, 15, 19, 18, 12, 11, 10, 15, 17, 20]

y = [9.5, 2.5, 3.6, 6.7, 5.2, 1, 0, 1.5, 2,
     3, 3.5, 4.5, 8.5, 7.5, 5, 4, 3, 5, 6.5, 10]

n = len(x)

x_square = [it ** 2 for it in x]

y_square = [it ** 2 for it in y]

x_multiply_y = [x * y for x, y in zip(x, y)]

sum_x = sum(x)  # -> 269
sum_y = sum(y)  # -> 92.5
sum_x_square = sum(x_square)  # -> 4029
sum_y_square = sum(y_square)  # -> 575.89
sum_x_multiply_y = sum(x_multiply_y)  # -> 1467

s_xx = sum_x_square - sum_x ** 2 / n  # -> 410.94
s_yy = sum_y_square - sum_y ** 2 / n  # -> 148.07
s_xy = sum_x_multiply_y - sum_x * sum_y / n  # 222.87
r = s_xy / (s_xx * s_yy) ** (1 / 2)  # 0.903


if __name__ == '__main__':
    try:
        data = pd.DataFrame(data={'study_hours': x, 'grade': y})
        sns.lmplot(x='study_hours', y='grade', data=data)
        plt.show()
    except KeyboardInterrupt:
        pass
