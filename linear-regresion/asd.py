import pandas as pd
import numpy as np
from scipy.special import expit as sigmoid

def load_iris():
    df = pd.read_csv('iris.csv', index_col=False)

    X = df.iloc[:, :-1].values
    y = df.iloc[:, -1].values

    labels = [
        'Iris-setosa',
        'Iris-versicolor',
        'Iris-virginica'
    ]

    for i, label in enumerate(labels):
        y[y == label] = i

    return {'data': X, 'target': y.astype('float'), 'target_names': labels}


def train_test_split(X, y, test_size=0.5, shuffle=True, seed=None):
    """ Split the data into train and test sets """
    if shuffle:
        X, y = shuffle_data(X, y, seed)
    # Split the training data from test data in the ratio specified in
    # test_size
    split_i = len(y) - int(len(y) // (1 / test_size))
    X_train, X_test = X[:split_i], X[split_i:]
    y_train, y_test = y[:split_i], y[split_i:]

    return X_train, X_test, y_train, y_test

def shuffle_data(X, y, seed=None):
    """ Random shuffle of the samples in X and y """
    if seed:
        np.random.seed(seed)
    idx = np.arange(X.shape[0])
    np.random.shuffle(idx)
    return X[idx], y[idx]


class LogisticRegression():
    def fit(self, X, y, n_iter=4000, lr=0.01):
        self.w = np.random.rand(X.shape[1])
        for _ in range(n_iter):
            self.w -= lr * (self.predict(X) - y).dot(X)
    def predict(self, X):
        return sigmoid(X.dot(self.w))


def main():
    # Load dataset
    data = load_iris()

    X = data['data']
    y = data['target']

    # Reduce to two classes
    X = X[y != 0]
    y = y[y != 0]
    y -= 1

    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.33, shuffle = True)

    clf = LogisticRegression()
    clf.fit(X_train, y_train)
    y_pred = np.rint(clf.predict(X_test))

    accuracy = np.mean(y_pred == y_test)
    print ("Accuracy:", accuracy)


if __name__ == "__main__":
    main()
