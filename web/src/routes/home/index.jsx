/* eslint-disable react/no-array-index-key */
/* eslint-disable no-console */
/* eslint-disable no-unused-vars */
/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { useState, useEffect } from 'react'
import axios from 'axios'
import { useHistory } from 'react-router-dom'

import Outline from '../../components/Outline'

import TextContent from '../../components/Content/TextContent'

import './styles.scss'

const Home = (props) => {
  const [data, setData] = useState([])
  const history = useHistory()

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios(
        'http://localhost:8080/checkpoint/8dd5f315-9788-4d00-87bb-10eed9eff566'
      )
      setData(result.data)
    }

    fetchData()
  }, [])

  return (
    <Outline>
      <TextContent>
        Kick-off Checkpoints by Jason Campos
        <div className="my-3 p-3 bg-white rounded shadow-sm">
          {data &&
            data.map((item, index) => (
              <div key={item.id} className="accordion" id="accordionColl">
                <div className="card">
                  <div className="card-header" id={`heading${index}`}>
                    <h2 className="mb-0">
                      <button
                        className="btn btn-link btn-block text-left"
                        type="button"
                        data-toggle="collapse"
                        data-target={`#collapse${index}`}
                        aria-expanded="false"
                        aria-controls={`collapse${index}`}
                      >
                        {item.name}
                      </button>
                      <div className="completion">
                        Completion % {item.complPercentage}
                      </div>
                    </h2>
                  </div>
                  <div
                    id={`collapse${index}`}
                    className="collapse"
                    aria-labelledby={`heading${index}`}
                    data-parent="#accordionColl"
                  >
                    <div className="card-body">
                      <table
                        className="table table-hover table-sm"
                        key={`${item.id}${index}`}
                      >
                        <thead className="thead-light">
                          <tr>
                            <th colSpan="2" scope="col">
                              Task
                            </th>
                            <th scope="col">Completion %</th>
                          </tr>
                        </thead>
                        <tbody>
                          {item.tasks.map((task, idx2) => (
                            <tr key={`${task.id}${idx2}`}>
                              <td colSpan="2">{task.text}</td>
                              <td>{task.complPercentage}</td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            ))}
        </div>
      </TextContent>
    </Outline>
  )
}

export default Home
