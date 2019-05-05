import React from "react"

import containerStyle from "./container.module.css"

export default props => (
  <div className={containerStyle.container}>{props.children}</div>
)
