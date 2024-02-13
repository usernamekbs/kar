import React, { useState, useEffect } from 'react';
import TextField from '@mui/material/TextField';
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import Button from "@mui/material/Button";
import { Typography } from "@mui/material";
import axios from "axios";
import { useNavigate,useLocation,useParams  } from 'react-router-dom';
import useUserStore from "../../stores/useUserStore"

const UpdatePost = () => {
    const location = useLocation().state.post
    const navigate = useNavigate();
    const [title, setTitle] = useState(location.title);
    const [content, setContent] = useState(location.content);
    const id = useParams().id;
    const categoryId = useParams().categoryId;
    const { user } = useUserStore();
    const headers = {
        'Authorization': user.accessToken
      }

    const updatePost = async () => {
        const data = {
            title       : title,
            content     : content,
        }
        
        try {
          await axios.put(`http://localhost:8080/api/post/update/${id}`,data,headers);
          navigate(`/category/${categoryId}/post`)
         
        } catch (error) {
          console.log(error);
        }
    };

    return (
        <Card sx={{ marginTop:'2%',marginLeft:'25%',minWidth: 275, maxWidth: "50vw", padding: 5 }}>
            <Box>
                <Typography variant="h5">게시글 수정</Typography>
            </Box>
            <Box height={"50vh"}>
                <TextField
                fullWidth
                label="제목"
                type="text"
                variant="standard"
                onChange={(e) => setTitle(e.target.value)}
                value={title}
                />
                <TextField
                fullWidth
                label="내용"
                type="text"
                variant="standard"
                onChange={(e) => setContent(e.target.value)}
                value={content}
                />
            </Box>
            <Button fullWidth onClick={() => updatePost()} variant="contained">
                저장
            </Button>
            
        </Card>
      );
}

export default UpdatePost;