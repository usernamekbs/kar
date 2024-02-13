import React, { useState, useEffect } from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import axios from "axios";
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import Button from "@mui/material/Button";
import Typography from '@mui/material/Typography';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
import { Container, TextField } from "@mui/material";
import { Link,useParams } from 'react-router-dom';
import useUserStore from "../../stores/useUserStore"

const Post = () => {
    const [postList,setPostList] = useState([]);
    const [postTotal,setPostTotal] = useState(0); //전체 개시글수
    const [postTotalPages,setPostTotalPages] = useState(0) //전체 페이징 수
    const [postPage,setPostPage] = useState(1)
    const [searchType, setSearchType] = useState("Title");
    const [searchText, setSearchText] = useState("");
    const category = useParams();
    const { user } = useUserStore();

    const StyledTableCell = styled(TableCell)(({ theme }) => ({
      [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
      },
      [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
      }, 
    }));
    
    const StyledTableRow = styled(TableRow)(({ theme }) => ({
      '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
      },
      // hide last border
      '&:last-child td, &:last-child th': {
        border: 0,
      },
    })); 
    
    const handleChange = (e,value) => {
      setPostPage(value); 
    };

    const fetchPost = async () => {
      
      try {  
        const result = await axios.get(`http://localhost:8080/api/post/list?page=${postPage}`,
        {params: {  
            searchText : searchText,
            searchType : searchType
          },
        })  
        setPostList(result.data.content) 
        setPostTotal(result.data.totalElements) 
        setPostTotalPages(result.data.totalPages)
      } catch (error) {
        console.log(error);
      }
    };

    useEffect(() => {
        fetchPost();
    }, [postPage]); 

    return (
      <>
        <TableContainer component={Paper} style={{marginTop:'20px'}}>
            <Typography variant="h5" gutterBottom >
              게시글 개수 : {postTotal}
            </Typography>
            
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
            <TableHead>
            <TableRow>
                <StyledTableCell>번호</StyledTableCell>
                <StyledTableCell align="right">카테고리</StyledTableCell>
                <StyledTableCell align="right">제목</StyledTableCell>
                <StyledTableCell align="right">작성자</StyledTableCell>
                <StyledTableCell align="right">조회수</StyledTableCell>
                <StyledTableCell align="right">파일개수</StyledTableCell>
                <StyledTableCell align="right">작성일</StyledTableCell>
                <StyledTableCell align="right">수정일</StyledTableCell>
                
            </TableRow>
            </TableHead>
            <TableBody>
            {postList.map((post) => (
              <StyledTableRow key={post.id}>
                <StyledTableCell component="th" scope="row">
                    <Link to={`/category/${category.categoryId}/post/view/${post.id}`}>{post.id}</Link>
                </StyledTableCell>
                <StyledTableCell align="right">{post.name}</StyledTableCell>
                <StyledTableCell align="right">{post.title} <span style={{color:'red'}}>[{post.commentCnt}]</span></StyledTableCell>
                <StyledTableCell align="right">{post.username}</StyledTableCell>
                <StyledTableCell align="right">{post.views}</StyledTableCell> 
                <StyledTableCell align="right">{post.imageCnt}</StyledTableCell>
                <StyledTableCell align="right">{post.createdDate}</StyledTableCell>
                <StyledTableCell align="right">{post.modifiedDate}</StyledTableCell>
                
              </StyledTableRow> 
            ))}
            </TableBody>
        </Table>
        </TableContainer>
        {user &&
              <Link to={`/category/${category.categoryId}/post/create`} style={{ textDecoration: "none", color:'white' }}>
                <Button variant="contained" color="primary">저장</Button>
              </Link>
            }
        <Stack spacing={2}>
          <Pagination style={{display:"flex",justifyContent:"center",marginTop:'10px'}} count={postTotalPages} page={postPage} onChange={handleChange} variant="outlined" color="primary" />
        </Stack>
        <Container maxWidth="md" sx={{ mt: 2 }}>
            <Select
              labelId="demo-simple-select-autowidth-label"
              id="demo-simple-select-autowidth"
              onChange={(e)=> setSearchType(e.target.value)}
              autoWidth
              label="search"
              defaultValue="Title"   
              value={searchType}
            >
              <MenuItem value="Title"><em>제목</em></MenuItem>
              <MenuItem value="Content">내용</MenuItem>
              <MenuItem value="Username">유저이름</MenuItem>
            </Select>
            <TextField  type="search" id="searchText" label="Search" sx={{ width: 600 }} onChange={(e) => setSearchText(e.target.value)}/>
            <Button style={{height:"56px"}} variant="contained" onClick={fetchPost}>검색</Button>
        </Container>
      </>
      );

}
export default Post;