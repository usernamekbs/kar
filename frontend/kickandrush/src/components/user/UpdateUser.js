import React, { useState, useEffect } from 'react';
import TextField from '@mui/material/TextField';
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import Button from "@mui/material/Button";
import { Typography } from "@mui/material";
import axios from "axios";
import Avatar from '@mui/material/Avatar';
import Stack from '@mui/material/Stack';
import { useNavigate,useLocation } from 'react-router-dom';
import useUserStore from "../../stores/useUserStore"

const UpdateUser = () => {
    const location = useLocation().state.user
    const navigate = useNavigate();
    const [username, setUsername] = useState(location.username);
    const [email, setEmail] = useState(location.email);
    const [nickname, setNickname] = useState(location.nickname);
    const { user } = useUserStore();

    const updateUser = async () => {
        const data = {
            username : username,
            email    : email,
            nickname : nickname
        }
        try {
          await axios.put(`http://localhost:8080/api/user/update/${location.id}`,data,{headers: { Authorization: `Bearer ${user.accessToken}` }});
          navigate(`/user`)
        
        } catch (error) {
          console.log(error);
        }
    };

    return (
        <Card sx={{ marginTop:'2%',marginLeft:'25%',minWidth: 275, maxWidth: "50vw", padding: 5 }}>
            <Box>
                <Typography variant="h5">회원정보 수정</Typography>
            </Box>
                <Box height={"50vh"}>
                    {/* <Stack direction="row" spacing={2}>
                    <Avatar src="/broken-image.jpg" />
                </Stack> */}
                <TextField
                fullWidth
                label="아이디"
                type="email"
                variant="standard" 
                onChange={(e) => setUsername(e.target.value)}
                value={username}
                />
                <TextField
                fullWidth
                label="닉네임"
                variant="standard"
                onChange={(e) => setNickname(e.target.value)}
                value={nickname}
                />
                <TextField
                fullWidth
                label="이메일 주소"
                type="email"
                variant="standard"
                onChange={(e) => setEmail(e.target.value)}
                value={email}
                />
                <Box sx={{ minWidth: 120 }}>
                    
                    {location.roles && location.roles.map((role) => (
                        <div key={role.roleId} value={role.name}>
                            {role.name} 
                            {/* <Button>삭제</Button> */}
                        </div>
                    ))} 
                     
                </Box>
            </Box>

            <Button fullWidth onClick={() => updateUser()} variant="contained">
                수정
            </Button>
        </Card>
      );
}

export default UpdateUser;