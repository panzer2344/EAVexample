package com.azino.eav.service.impl;

import com.azino.eav.constants.UserConstants;
import com.azino.eav.model.User;
import com.azino.eav.model.eav.Attribute;
import com.azino.eav.model.eav.Entity;
import com.azino.eav.model.eav.Value;
import com.azino.eav.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends BaseEntitiesServiceImpl<User> implements UserService {

    @Override
    public User save(User user) {
        try {

            Field firstNameField = User.class.getDeclaredField(UserConstants.USER_FIRST_NAME_VALUE);
            Field lastNameField = User.class.getDeclaredField(UserConstants.USER_LAST_NAME_VALUE);
            Field ageField = User.class.getDeclaredField(UserConstants.USER_AGE_VALUE);
            Field usernameField = User.class.getDeclaredField(UserConstants.USER_USERNAME_VALUE);
            Field idField = User.class.getDeclaredField(UserConstants.USER_ID_VALUE);

            Long userId = (Long) getFieldValue(idField, user);
            String username = (String) getFieldValue(usernameField, user);
            String firstName = (String) getFieldValue(firstNameField, user);
            String lastName = (String) getFieldValue(lastNameField, user);
            Short age = (Short) getFieldValue(ageField, user);

            Entity userEntity = null;
            if(null != userId) {
                userEntity = entityService.findById(userId);
                if(null != userEntity){
                    if(null != userEntity.getType()){
                        if(!userEntity.getType().equals(User.class.toString()));
                    }
                }
            }
            if(null == userEntity){
                userEntity = new Entity(null, username, User.class.toString());
                userEntity = entityService.save(userEntity);
                if(null == userEntity){
                    System.out.println("trouble with saving userEntity with name " + username);
                    return null;
                }
            }

            Attribute usernameAttribute = findOrSaveAttribute(usernameField);
            Attribute firstNameAttribute = findOrSaveAttribute(firstNameField);
            Attribute lastNameAttribute = findOrSaveAttribute(lastNameField);
            Attribute ageAttribute = findOrSaveAttribute(ageField);

            Value usernameValue = saveAttributeValue(usernameAttribute, userEntity, username);
            Value firstNameValue = saveAttributeValue(firstNameAttribute, userEntity, firstName);
            Value lastNameValue = saveAttributeValue(lastNameAttribute, userEntity, lastName);
            Value ageValue = saveAttributeValue(ageAttribute, userEntity, age.toString());

            user.setId(userEntity.getId());
            return user;


        }catch (NoSuchFieldException nsfe){
            nsfe.printStackTrace();
        } catch (IllegalAccessException iae){
            iae.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(User entity) {
        if(null != entity.getId()){
            valueService.deleteAllByEntity_Id(entity.getId());
            entityService.deleteById(entity.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        valueService.deleteAllByEntity_Id(id);
        entityService.deleteById(id);
    }

    @Override
    public User update(Long id, User entity) {
        User userDb = findById(id);

        userDb.setUsername(entity.getUsername());
        userDb.setFirstName(entity.getFirstName());
        userDb.setLastName(entity.getLastName());
        userDb.setAge(entity.getAge());

        return userDb;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        List<Long> ids = entityService.findIdsByType(User.class.toString());
        ids.forEach(id -> {
            users.add(findById(id));
        });
        return users;
    }

    @Override
    public User findById(Long id) {
        List<Value> values = valueService.findAllByEntity_Id(id);

        String username = null;
        Long userId = id;
        String userFirstName = null;
        String userLastName = null;
        Short userAge = null;

        for(Value value : values){
            String valueName = value.getAttribute().getName();
            switch (valueName){
                case UserConstants.USER_ID_VALUE:
                    userId = Long.parseLong(value.getValue());
                    break;
                case UserConstants.USER_USERNAME_VALUE:
                    username = value.getValue();
                    break;
                case UserConstants.USER_FIRST_NAME_VALUE:
                    userFirstName = value.getValue();
                    break;
                case UserConstants.USER_LAST_NAME_VALUE:
                    userLastName = value.getValue();
                    break;
                case UserConstants.USER_AGE_VALUE:
                    userAge = Short.parseShort(value.getValue());
                    break;
                default:
                    System.out.println("user find by id error: cannot set property");
            }
        }

        if(null == username || null == userId){
            return null;
        }
        User user = new User(
                userId,
                username,
                userFirstName,
                userLastName,
                userAge
        );

        return user;
    }


    /* <----------------> */
    /* <----------------> */
    /* Utility functions */
    /* <----------------> */
    /* <----------------> */

    protected Attribute findOrSaveAttribute(Field field){
        Attribute attribute = attributeService.findByName(field.getName());
        if(null == attribute){
            attribute = new Attribute(null, field.getName());
            attribute = attributeService.save(attribute);
            if(null == attribute){
                System.out.println("trouble with saving userAttribute with name " + attribute.getName());
            }
        }
        return attribute;
    }

    protected Value saveAttributeValue(Attribute attribute, Entity entity, String value){
        Value attributeValue = new Value(
                null,
                attribute, //firstNameAttribute,
                entity, //userEntity,
                value //firstName
        );
        attributeValue = valueService.save(attributeValue);
        if(null == attributeValue){
            System.out.println("trouble with saving userAttributeValue with attr name " + attribute.getName());
        }
        return attributeValue;
    }

    protected Long getFieldValueLong(Field field, User user) throws IllegalAccessException {
        field.setAccessible(true);
        Long fieldValue = field.getLong(user);
        field.setAccessible(false);
        return fieldValue;
    }

    protected Short getFieldValueShort(Field field, User user) throws IllegalAccessException {
        field.setAccessible(true);
        Short fieldValue = field.getShort(user);
        field.setAccessible(false);
        return fieldValue;
    }

    protected Object getFieldValue(Field field, User user) throws IllegalAccessException {
        field.setAccessible(true);
        Object fieldValue = field.get(user);
        field.setAccessible(false);
        return fieldValue;
    }

}
