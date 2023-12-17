import React from 'react'
import Authentication from '../components/authentication'
import axios from  '../config/axios'
import { context } from '../context/context'
import { useEffect, useState } from 'react'

const login = () => {
  return (
    <context.Provider>
          <Authentication/>
    </context.Provider>
  )
}

export default login
