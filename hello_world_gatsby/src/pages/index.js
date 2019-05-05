import React from "react"
import { Link, graphql } from "gatsby"
import Header from "../components/header"
import Container from "../components/container"

export default ({ data }) => {
  console.log(data)

  return (
    <div style={{ color: "purple" }}>
      <Container>
        <Link to="/contact/">Contact</Link>
        <Header headerText={data.site.siteMetadata.title} />
        <p>What a world.</p>
        <img src="https://source.unsplash.com/random/400x200" alt="" />
        <h2>Posts</h2>
        <ul>
          {data.allMarkdownRemark.edges.map(({ node }) => (
            <li key={node.id}>
              {node.frontmatter.title} - {node.frontmatter.date}
            </li>
          ))}
        </ul>
      </Container>
    </div>
  )
}

export const query = graphql`
  query {
    site {
      siteMetadata {
        title
      }
    }
    allMarkdownRemark {
      edges {
        node {
          id
          frontmatter {
            title
            date(formatString: "DD MMMM, YYYY")
          }
          html
          timeToRead
          excerpt
        }
      }
    }
  }
`
