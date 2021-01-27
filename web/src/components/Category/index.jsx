/* eslint-disable no-eval */
/* eslint-disable react/no-array-index-key */
/* eslint-disable no-console */
/* eslint-disable no-unused-vars */
/* eslint-disable jsx-a11y/no-static-element-interactions */
/* eslint-disable no-script-url */
/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { useState, useEffect } from 'react'
import Header from '../Header'
import Api from '../../utils/api'

import './styles.scss'

const Category = (props) => {
  const { chapter, lesson } = props
  // const [enableFilter, setEnableFilter] = useState(false)
  const [data, setData] = useState({ toc: '' })

  const openNav = () => {
    document.getElementById('mySidenav').style.width = '350px'
  }

  const closeNav = () => {
    document.getElementById('mySidenav').style.width = '0'
  }

  const handleKeyDown = () => {}

  const api = new Api()

  useEffect(() => {
    const fetchData = async () => {
      let result = ''
      // api.getToc('lectureIdPath').then((response) => (result = response))
      await api.getToc('lectureIdPath').then(
        (response) => {
          result = response
          console.log(response) // -> This will display the resp properly
        },
        (error) => {
          console.log(error)
        }
      )
      setData({ toc: result.data })
      // setData(result.data)
    }

    fetchData()
    // console.log(data) ----> This will display blank
  }, [])

  return (
    <div className="container">
      <div id="mySidenav" className="sidenav">
        <a href="#" className="closebtn" onClick={closeNav}>
          X
        </a>
        <div id="toc_container">
          <p className="toc_title">{data.toc.name}</p>
          <ul className="toc_list">
            {data.toc.childTocs &&
              data.toc.childTocs.map((res, i) => (
                <li key={`t-${i}`}>
                  <a href="#First_Point_Header">
                    <div className="chapterTitle">
                      {`${eval('i + 1')}. `}
                      {res.name}
                    </div>
                  </a>
                  <ul>
                    {res.topic &&
                      res.topic.map((topic, idx2) => (
                        <li key={`st-${idx2}`}>
                          <a href={`${topic.pathId}`}>{topic.name}</a>
                        </li>
                      ))}
                  </ul>
                </li>
              ))}
          </ul>
        </div>
      </div>
      <Header />
      <main role="main" className="flex-shrink-0">
        <div className="container-fluid">
          {props.children({ openNav, handleKeyDown })}
        </div>
      </main>
    </div>
  )
}

export default Category
