import { create } from 'zustand';
import { persist,createJSONStorage  } from 'zustand/middleware'

const useUserStore = create(persist((set) => ({
    user: null,
    setUser: (user) => {
        set((state) => ({...state, user }));
    },
    removeUser: () => {
        set((state) => ({...state, user : null }));
    },
    }),
    {
        name: 'user', // name of the item in the storage (must be unique)
        storage: createJSONStorage(() => localStorage), // (optional) by default, 'localStorage' is used
      }
    )
);

export default useUserStore;