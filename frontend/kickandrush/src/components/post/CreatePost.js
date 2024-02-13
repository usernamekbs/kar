import React, { useState, useEffect } from 'react';
import TextField from '@mui/material/TextField';
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import Button from "@mui/material/Button";
import { Typography } from "@mui/material";
import axios from "axios";
import { useNavigate  } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import AddToPhotosIcon from '@mui/icons-material/AddToPhotos';
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import ImageListItemBar from '@mui/material/ImageListItemBar';
import useUserStore from "../../stores/useUserStore"

const CreatePost = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const categoryId = useParams().categoryId;
    const [readerImages,setReaderImages] = useState([]);
    const navigate = useNavigate();
    const { user } = useUserStore();

    const handleChange = e => {
        const images = e.target.files;
        for (let i = 0; i < images.length; i++) {
            //파일을 읽어오기위한 객체  
            const reader = new FileReader();

            reader.onload = (e) => {
                setReaderImages((prevImages) => [
                    ...prevImages,
                    {
                        file: images[i],
                        previewUrl: e.target.result,
                    },
                ]);
            };
            //파일이나 blob을 처리함 파일의 url을 읽어드림.
            reader.readAsDataURL(images[i]);
        }
    };

    const createPost = async(e) => {
        e.preventDefault()
        const formData = new FormData();
        const config = {
            headers: {
                "content-type": "multipart/form-data",
                'x-access-token': user.accessToken
            }
        }
        const data = {
            title       : title,
            content     : content,
            categoryId  : categoryId,
            userId    : user.id 
        }
    
        readerImages.map((i)=>{
            formData.append("files", i.file);
        })

        formData.append("requestPostDto", new Blob([JSON.stringify(data)], {
            type: "application/json"
        }));

        try {
            await axios.post(`http://localhost:8080/api/post/create/`,formData,config);
            navigate(`/category/${categoryId}/post`)
        
        } catch (error) { 
            console.log(error);
        }
    };
   
    const deletePreviewImage = (index) => {
        //한개만 삭제
        const files = readerImages.filter((f,idx)=> idx !== index) 
        setReaderImages(files)
    };

    //해당 함수 안만들어주면 같은 파일 업로드시 중복이 됨
    const handleClick = (e) => {
        e.target.value = '';
    };

    useEffect(() => {
    }, [readerImages]); 

    return (
        <Card sx={{ marginTop:'2%',marginLeft:'25%',minWidth: 275, maxWidth: "50vw", padding: 5 }}>
            <Box>
                <Typography variant="h5">게시글 쓰기</Typography>
            </Box>
            <Box height={"100vh"}>
                <TextField
                fullWidth
                label="제목"
                type="text"
                variant="standard"
                onChange={(e) => setTitle(e.target.value)}
                />
                <TextField
                fullWidth
                label="내용"
                type="text"
                variant="standard"
                onChange={(e) => setContent(e.target.value)}
                />
                <Button style ={{marginTop:'20px',width:'100%'}}variant="contained" component="label" color="primary">
                    {" "}
                    <AddToPhotosIcon /> Upload a file
                    <input type="file" multiple 
                        onClick={(e)=>handleClick(e)}
                        onChange={handleChange} hidden/>
                </Button>
                <ImageList sx={{ width: 500, height: 450 }} cols={2} rowHeight={164}>
                    {readerImages.map((image, index) => (
                        <ImageListItem key={index}>
                            <img 
                                srcSet={image.previewUrl}
                                src={image.previewUrl}
                                alt={image.file.name}
                                loading="lazy"
                            />
                            <ImageListItemBar
                                title={<Button style={{backgroundColor:"#d32f2f",color:"white"}} onClick={()=> deletePreviewImage(index)}>{image.file.name} X </Button> }
                                position="below"
                            />
                        </ImageListItem>
                    ))}
                </ImageList>
            </Box>
            
            <Button fullWidth onClick={createPost} variant="contained">
                저장
            </Button>
            
        </Card>
      );
}

export default CreatePost;