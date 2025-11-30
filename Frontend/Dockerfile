FROM node:20-alpine

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 3000

#RUN npm run build

ENV REACT_APP_API_URL=http://localhost:8080/api

CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]