export const requestService = {
  post(url, body, header = {}) {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve({ url, body, header })
      }, 2500)
    })
  },
}
