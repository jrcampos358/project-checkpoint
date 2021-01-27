/* eslint-disable spaced-comment */
import React from 'react'
import { render } from 'react-dom'
import {
  BrowserRouter as Router,
  //Switch,
  Route,
  //useRouteMatch,
} from 'react-router-dom'

import Home from './routes/home'

import './index.scss'

render(
  <Router>
    <Route path="/" component={Home} exact />
  </Router>,
  document.getElementById('root')
)
