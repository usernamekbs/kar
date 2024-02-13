import React, { useState, useEffect } from 'react';
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import axios from "axios";
import useUserStore from "../../stores/useUserStore"
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { setUser } = useUserStore();
    const navigate = useNavigate();

    const handleSubmit = async () => {
        const data = {
            username : username,
            password : password
        }
        try {
            const result = await axios.post(`http://localhost:8080/api/auth/login`,data);
            setUser(result.data)
            navigate(`/category/1/post`) 
            
        } catch (error) {
            console.log(error);
        }
    };
   

    return (
        <Container component="main" maxWidth="xs">
            <Box
                sx={{  
                marginTop: 8,
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                }}
            >
            <Typography component="h1" variant="h5">
                Sign in
            </Typography>
            <Box component="form" noValidate sx={{ mt: 1 }}>
            <TextField
                margin="normal"
                required
                fullWidth
                id="username"
                label="Username"
                name="username"
                autoComplete="username"
                autoFocus
                onChange={(e) => setUsername(e.target.value)}
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                onChange={(e) => setPassword(e.target.value)}
            />
            <FormControlLabel
                control={<Checkbox value="remember" color="primary" />}
                label="Remember me"
            />
            <Button
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                onClick={() => handleSubmit()}
            >
                Sign In
            </Button>
            <Grid container>
                <Grid item xs>
                <Link href="#" variant="body2">
                    Forgot password?
                </Link>
                </Grid>
                <Grid item>
                <Link href="/user/create" variant="body2">
                    {"Don't have an account? Sign Up"}
                </Link>
                </Grid>
            </Grid>
            </Box>
            </Box>
        </Container>
      );

}
export default Login;