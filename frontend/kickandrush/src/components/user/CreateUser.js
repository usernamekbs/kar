import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import Button from "@mui/material/Button";
import { Typography } from "@mui/material";
import axios from "axios";
import { useNavigate  } from 'react-router-dom';

const CreateUser = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [nickname, setNickname] = useState('');
    const navigate = useNavigate();
    
    //회원가입이라토큰 필요없음.
    const createUser = async () => {
        const data = {
            username : username,
            password : password,
            email    : email,
            nickname : nickname
        }
        try {
          await axios.post(`http://localhost:8080/api/user/create/`,data);
          navigate(`/category/1/post`)
        
        } catch (error) {
          console.log(error);
        }
    };

    return (
        <Card sx={{ marginTop:'2%',marginLeft:'25%',minWidth: 275, maxWidth: "50vw", padding: 5 }}>
            <Box>
                <Typography variant="h5">회원가입</Typography>
            </Box>
            <Box height={"50vh"}>
                <TextField
                fullWidth
                label="아이디"
                type="email"
                variant="standard"
                onChange={(e) => setUsername(e.target.value)}
                />
                <TextField
                fullWidth
                label="비밀번호"
                type="password"
                variant="standard"
                onChange={(e) => setPassword(e.target.value)}
                />
                <TextField
                fullWidth
                label="닉네임"
                variant="standard"
                onChange={(e) => setNickname(e.target.value)}
                />
                <TextField
                fullWidth
                label="이메일 주소"
                type="email"
                variant="standard"
                onChange={(e) => setEmail(e.target.value)}
                />
            </Box>

            <Button fullWidth onClick={() => createUser()} variant="contained">
                회원가입
            </Button>
            <Box component="div" display="flex" mt={2}>
                <Typography>이미 계정이 있으신가요?</Typography>
                <Typography fontWeight={800} ml={1} >
                로그인
                </Typography>
            </Box>
        </Card>
      );
}

export default CreateUser;