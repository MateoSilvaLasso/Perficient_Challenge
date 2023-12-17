import React from 'react'
import Register from '../components/CreateUser'
import axios from  '../config/axios'
import { context } from '../context/context'
import { useEffect, useState } from 'react'

const register = () => {
    return(
        <context.Provider>
            <Register/>
        </context.Provider>
    )
}

export default register

