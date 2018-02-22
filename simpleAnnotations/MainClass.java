package simpleAnnotations;

public class MainClass {
    public static void main(String[] args) {
        User user = new User();
        Class<?> actionClass = new UserDeleteAction().getClass();
        PermissionRequired permissionRequired = actionClass.getAnnotation(PermissionRequired.class);
        if (permissionRequired != null){
            if(user != null && user.getPermissions().contains(permissionRequired.value())){
                System.out.println("Your permission is confirmed. Now you can delete users.");
            }
        }
    }
}
