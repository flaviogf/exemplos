FROM ruby:2.6
WORKDIR /app
COPY . .
RUN gem install --no-document bundler \
    && bundle config --local frozen true \
    && bundle config --local without "development test" \
    && bundle install
ENV PORT=8080
ENTRYPOINT [ "bundle", "exec", "functions-framework-ruby" ]
