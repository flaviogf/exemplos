import React from "react"
import { graphql } from "gatsby"
import Container from "../components/container"

export default ({ data }) => {
  console.log(data)

  return (
    <Container>
      <h1>My files</h1>

      <table>
        <thead>
          <tr>
            <th>relativePath</th>
            <th>prettySize</th>
            <th>extension</th>
            <th>birthTime</th>
          </tr>
        </thead>

        <tbody>
          {data.allFile.edges.map(({ node }) => (
            <tr key={node.id}>
              <td>{node.relativePath}</td>
              <td>{node.prettySize}</td>
              <td>{node.extension}</td>
              <td>{node.birthTime}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </Container>
  )
}

export const query = graphql`
  query {
    allFile {
      edges {
        node {
          id
          relativePath
          prettySize
          extension
          birthTime(fromNow: true)
        }
      }
    }
  }
`
