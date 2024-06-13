import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
import os
import csv
from sklearn.metrics.pairwise import linear_kernel
from train import train_model

# Load the movie data
movies = pd.read_csv(r"C:\Users\admin\Desktop\DATN\data\imdb-movies-dataset.csv")


file_path = r"C:\Users\admin\Desktop\DATN\data\favorite.txt"
favorite_movies = []

with open(file_path, 'r') as file:
    lines = file.readlines()
    for line in lines:
        favorite_movies.append(line.strip())  


movies['Title'] = movies['Title'].str.lower()

cosine_sim, indices, movies = train_model(movies)

indices = pd.Series(movies.index, index=movies['Title']).to_dict()

def get_recommendations(favorite_movies, cosine_sim=cosine_sim):
    sim_scores = []
    for movie in favorite_movies:
        idx = indices[movie.lower()]
        sim_scores.extend(list(enumerate(cosine_sim[idx])))
    
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)

    movie_indices = [i[0] for i in sim_scores]

    recommended_movies = []
    for idx in movie_indices:
        movie_title = movies['Title'].iloc[idx]
        if movie_title not in [m.lower() for m in favorite_movies] and movie_title not in recommended_movies:
            recommended_movies.append((idx, movie_title))
        if len(recommended_movies) >= 12:
            break

    return recommended_movies

recommendations = get_recommendations(favorite_movies)

result = [(idx + 1, title) for idx, title in recommendations]

if os.path.exists('C:/Users/admin/Desktop/DATN/data/favorite-result.csv'):
    os.remove('C:/Users/admin/Desktop/DATN/data/favorite-result.csv')

with open('C:/Users/admin/Desktop/DATN/data/favorite-result.csv', 'w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(['id', 'Title'])
    writer.writerows(result)

